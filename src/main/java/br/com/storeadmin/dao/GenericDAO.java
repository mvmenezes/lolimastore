package br.com.storeadmin.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	
	public void save(T entity) throws Exception {
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
			erro.printStackTrace();;
			manager.getTransaction().rollback();
			throw new Exception(erro.getMessage());
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
			manager.getTransaction().rollback();
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
		
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			manager.getTransaction().rollback();
		}
	}

	
	public void delete(T entity)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
		
			manager.getTransaction().begin();
			if(!manager.contains(entity))
				entity = manager.merge(entity);
			manager.remove(entity);
			manager.getTransaction().commit();
		
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			manager.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String jpql, Object... params)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			Query query = manager.createQuery(jpql);
			
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
			
			
			List<T> entities = query.getResultList();
			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			manager.getTransaction().rollback();
			return null;
		}
		
	}
	@SuppressWarnings("unchecked")
	public T findOne(String jpql, Object... params)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			Query query = manager.createQuery(jpql);
			
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
			T entities = (T) query.getSingleResult();
			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> findByJPQL(String jpql)
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			Query query = manager.createQuery("from "+ aClass.getSimpleName() + "p "+ jpql);
			List<T> entities = query.getResultList();
			return entities;
		}catch(Exception erro)
		{
			manager.getTransaction().rollback();
			System.out.println(erro.getMessage());
			return null;
		}
		
	}
	public T findByID(long id)
	{
		//EntityManager manager = null;
		try
		{
			manager  = getEntityManager();
			T entity = (T) manager.find(aClass, id);
			return entity;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			manager.getTransaction().rollback();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Set<T> findAll()
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			Query query = manager.createQuery("from "+ aClass.getSimpleName());
			Set<T> entities = new HashSet<T>();
			entities.addAll(query.getResultList());
			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			manager.getTransaction().rollback();
			return null;
		}
	}

	public List<T> findAllList()
	{
		//EntityManager manager = null;
		try
		{
			manager = getEntityManager();
			Query query = manager.createQuery("from "+ aClass.getSimpleName());
			List<T> entities =query.getResultList();
			return entities;
		}catch(Exception erro)
		{
			System.out.println(erro.getMessage());
			return null;
		}
	}
}
