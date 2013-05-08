(ns appengy-redis.core-test
  (:import [appengy.session EdnSerializer]
           [appengy_redis.core RedisSession])
  (:require appengy-redis.core)
  (:use clojure.test
        appengy.session))

(deftest test-redis-session []
  (let [sess "1"
        k1 "hello"
        v1 "world"
        s (RedisSession. (EdnSerializer.) {:host "localhost"})]
    (.clean s sess)
    (.setData s sess k1 v1)
    (is (= v1 (.getData s sess k1)))
    (.clean s sess)
    (is (nil? (.getData s sess k1)))))