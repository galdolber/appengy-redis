(ns appengy-redis.core
  (:import [appengy.session Session])
  (:require [redis.core :as redis]))

(deftype RedisSession [session-serializer conf] Session
  (getData [this session k]
   (require 'redis.core)
   (redis/with-server (into {} (for [[k v] conf] [(keyword k) v]))
    (if-let [v (redis/hget session k)]
      (.deserialize session-serializer v))))
  (setData [this session k v]
   (require 'redis.core)
   (try
     (redis/with-server (into {} (for [[k v] conf] [(keyword k) v]))
      (if v
        (redis/hset session k
                    (.serialize session-serializer v))
        (do
          (redis/hdel session k)
          (if (zero? (redis/hlen session))
            (redis/del session))))
      (catch Exception e nil))))
  (clean [this session]
   (require 'redis.core)
   (redis/with-server (into {} (for [[k v] conf] [(keyword k) v]))
    (redis/del session))))