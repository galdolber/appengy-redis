(ns appengy-redis.core
  (:import [appengy.session Session])
  (:require [redis.core :as redis]))

(defn get-conf [conf]
  (into {}
        (for [[k v] conf]
          [(keyword k) v])))

(deftype RedisSession [session-serializer conf] Session
  (getData [this session k]
   (redis/with-server (get-conf conf)
    (if-let [v (redis/hget session k)]
      (.deserialize session-serializer v))))
  (setData [this session k v]
   (try
     (redis/with-server (get-conf conf)
      (if v
        (redis/hset session k
                    (.serialize session-serializer v))
        (do
          (redis/hdel session k)
          (if (zero? (redis/hlen session))
            (redis/del session))))
      (catch Exception e nil))))
  (clean [this session]
   (redis/with-server (get-conf conf)
    (redis/del session))))