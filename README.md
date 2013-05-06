# appengy-redis

Redis sessions for [Appengy](http://github.com/galdolber/appengy)

## Usage

Java
```
import appengy-redis.core.RedisSession;

Map<String, Object> conf = new ...;
conf.put("host", "127.0.0.1");
new RedisSession(new EdnSerializer(), conf);
```

Clojure
```
(RedisSession. (EdnSerializer.) {:host "127.0.0.1"})
```

Options
```
host     "127.0.0.1"
port     6379
db       0
timeout  5000
password nil"
```

## License

The use and distribution terms for this software are covered by the
Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
which can be found in the file LICENSE.html at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by
the terms of this license.
You must not remove this notice, or any other, from this software.
