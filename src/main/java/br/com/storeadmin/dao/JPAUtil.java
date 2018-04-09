package br.com.storeadmin.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.spi.PersistenceUnitInfo;



public class JPAUtil {

	public EntityManagerFactory factory;
	
	private static JPAUtil instance;
	
	private JPAUtil()
	{

		try
		{
		    this.factory = Persistence.createEntityManagerFactory("storeadmin");
			instance = this;
		}catch(Exception erro)
		{
			int r = 3;
		}
	}
	
	public static synchronized JPAUtil getInstance() {
		if(instance == null)
		{
			return new JPAUtil();
		}
		else
		{
			return instance;
		}
	}
	
	public EntityManager getEntityManager()
	{
		return factory.createEntityManager();
	}
}
