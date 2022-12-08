package am.jsl.personalfinances.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring managed EhCache configuration.
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

/**
*Tworzy menedżera pamięci podręcznej {@link Ehcache} z odpowiednimi konfiguracjami pamięci podręcznej.
*Służy do przechowywania wyników zapytań userByName, walut, wyszukiwania transakcji.
*@return CacheManager
*/
	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		System.setProperty("net.sf.ehcache.skipUpdateCheck", "false");

		//pamięć podręczna użytkownika według nazwy
		net.sf.ehcache.CacheManager manager = net.sf.ehcache.CacheManager.create();

		CacheConfiguration cacheConfig = new CacheConfiguration();
		cacheConfig.setName("userByName");
		cacheConfig.maxEntriesLocalHeap(200);
		cacheConfig.maxEntriesLocalDisk(10);
		cacheConfig.timeToLiveSeconds(3600*2);
		cacheConfig.timeToIdleSeconds(0);
		Cache cache = new Cache(cacheConfig);
		manager.addCache(cache);

		//pamięć podręczna walut
		cacheConfig = new CacheConfiguration();
		cacheConfig.setName("currencies");
		cacheConfig.maxEntriesLocalHeap(200);
		cacheConfig.maxEntriesLocalDisk(200);
		cache = new Cache(cacheConfig);
		manager.addCache(cache);

		//pamięć podręczna kursów walut
		cacheConfig = new CacheConfiguration();
		cacheConfig.setName("rates");
		cacheConfig.maxEntriesLocalHeap(200);
		cacheConfig.maxEntriesLocalDisk(200);
		cache = new Cache(cacheConfig);
		manager.addCache(cache);

		//Pamięć podręczna wyszukiwania transakcji
		cacheConfig = new CacheConfiguration();
		cacheConfig.setName("transactionSearch");
		cacheConfig.maxEntriesLocalHeap(200);
		cacheConfig.maxEntriesLocalDisk(10);
		cacheConfig.timeToLiveSeconds(3600*2);
		cache = new Cache(cacheConfig);
		manager.addCache(cache);
		return manager;
	}

/**
*Tworzy CacheManager wspierany przez menedżera EhCache.
*@return CacheManager wspierany przez menedżera EhCache.
*/
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
}
