package com.app.shoutbox.utilities;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//to create singleton , immutable , inherently thrd safe  SF obj
public class HibernateUtils
{
	private static SessionFactory sf;
	static Logger log=Logger.getLogger(HibernateInitializer.class);
	static
	{
		log.info("in static init block");
		try
		{
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			log.info("sf created...");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static SessionFactory getSf()
	{
		return sf;
	}

}