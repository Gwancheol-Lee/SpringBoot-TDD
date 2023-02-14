package com.example.productorderservice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.google.common.base.CaseFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import jakarta.transaction.Transactional;

@Component
public class DatabaseCleanup implements InitializingBean{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private List<String> tableNames;
	
	@Override
	public void afterPropertiesSet() {
		// 현재 Java에서 돌고있는 모든 JPA Entity를 가져옴
		final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		// stream을 돌면서 @Entity, @Table 어노테이션이 있는 것들을 가져와서 List에 담는다.
		tableNames = entities.stream()
				.filter(e -> isEntity(e) && hasTableAnnotation(e))
				.map(e -> e.getJavaType().getAnnotation(Table.class).name())
				.collect(Collectors.toList());
		
		// @Entity 어노테이션은 있지만 @Table 어노테이션이 없는 것들은 UPPER_CAMEL -> LOWER_UNDERSCORE 포맷
		// ex) ProductItem -> product_item
		final List<String> entityNames = entities.stream()
				.filter(e -> isEntity(e) && !hasTableAnnotation(e))
				.map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
				.toList();
		
		tableNames.addAll(entityNames);
	}
	
	private boolean isEntity(EntityType<?> e) {
		return null != e.getJavaType().getAnnotation(Entity.class);
	}
	
	private boolean hasTableAnnotation(EntityType<?> e) {
		return null != e.getJavaType().getAnnotation(Entity.class);
	}

	@Transactional
	public void execute() {
		entityManager.flush();
		// 테이블 행 삭제 시 Foreign Key로 인해 삭제가 불가능한 상왕을 무시하도록 세팅
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
		
		for (final String tableName : tableNames) {
			// 각 테이블의 모든 행 삭제
			entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
			// 각 테이블 컬럼 ID값 1로 초기화
			entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
		}
		
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
	}

}
