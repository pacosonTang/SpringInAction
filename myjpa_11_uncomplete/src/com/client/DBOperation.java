package com.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Employee;

public class DBOperation {
	public static void main(String[] args) {
		insert();		
	}
	
	public static void insert() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Employee employee = new Employee();
		employee.setId(1201);
		employee.setName("Gopal");
		employee.setSalary(40000);		

		entitymanager.persist(employee);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		System.out.println("insert successfully.");
	}
}
