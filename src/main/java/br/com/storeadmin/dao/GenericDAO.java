package br.com.storeadmin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDAO < T extends Serializable> {
	
	private Class<T> aClass;
	
	EntityManager managerMaster;
	EntityManager manager;
	public GenericDAO(Class<T> aClass) {
		this.aClass = aClass;
		manager = getEntityManager();
	}
	
	protected EntityManager getEntityManager()
	{
		
		return JPAUtil.getInstance().getEntityManager();
	}

	
	public void save(T entity)
	{
		//EntityManager manager = null;
		try
		{
			
		manager = getEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(entity);

		manager.flush();
		manager.getTransaction().commit();
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			manager.close();
		}
		
	}		
	public void saveAll(List<T> entities)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			for(int i = 0; i < entities.size(); i++)
			{
				manager.getTransaction().begin();
				manager.persist(entities.get(i));
				manager.getTransaction().commit();
			}
		
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
	}	
	public void saveMaster(T entity)
	{
		managerMaster.persist(entity);
	}	



	
	public void update(T entity)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			manager.getTransaction().begin();
			manager.merge(entity);
			manager.getTransaction().commit();
			manager.close();
		
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
	}

	
	public void delete(T entity)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
		
			manager.getTransaction().begin();
			manager.remove(entity);
			manager.getTransaction().commit();
			manager.close();
		
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String jpql, Object... params)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			manager.getTransaction().begin();
	
			Query query = manager.createQuery(jpql);
			
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
			
			
			List<T> entities = query.getResultList();
			manager.getTransaction().commit();

			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			return null;
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
		
	}
	@SuppressWarnings("unchecked")
	public T findOne(String jpql, Object... params)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			manager.getTransaction().begin();
	
			Query query = manager.createQuery(jpql);
			
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
			
			
			T entities = (T) query.getSingleResult();
			manager.getTransaction().commit();
			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			return null;
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> findByJPQL(String jpql)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			manager.getTransaction().begin();
	
			Query query = manager.createQuery("from "+ aClass.getSimpleName() + "p "+ jpql);
			
			List<T> entities = query.getResultList();
			manager.getTransaction().commit();
			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			return null;
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
		
	}
	public T findByID(long id)
	{
		//EntityManager manager = null;
		try
		{
			manager  = getEntityManager();
			manager.getTransaction().begin();
			T entity = (T) manager.find(aClass, id);
			manager.getTransaction().commit();
			return entity;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			return null;
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll()
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			manager.getTransaction().begin();
	
			Query query = manager.createQuery("from "+ aClass.getSimpleName());
			
			List<T> entities = query.getResultList();
			manager.getTransaction().commit();
			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			return null;
		}finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			if (manager.isOpen())
				manager.close();
		}
	}
}
