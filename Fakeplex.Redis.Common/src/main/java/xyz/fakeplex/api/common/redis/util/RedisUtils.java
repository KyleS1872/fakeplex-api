package xyz.fakeplex.api.common.redis.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Kyle
 */
public class RedisUtils {

  public static final Gson gson =
      new GsonBuilder()
          .setFieldNamingStrategy(new RedisFieldNamingStrategy())
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
          .create();
}
