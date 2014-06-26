# clj-kvsn

KVSN是一种最简单的key/value文本格式，只支持一层kv数据，并且数据类型只有字符串。

这是一种比json更简洁的文本结构，比json的性能强很多倍，使用・data.json`测试，性能超出json 7-30倍，适用在高性能要求场景。

## 格式

```
k1 : v1
k2 : v2
k3:
  v31
  v32
```

clj-kvsn 是一个kvsn的clojure实现。

## Installation

`clj-kvsn` is available as a Maven artifact from
[Clojars](http://clojars.org/huzhengquan/clj-kvsn):

```clojure
[huzhengquan/clj-kvsn "0.1.1"]
```

## Usage


```clojure
(require '[clj-kvsn :as kvsn])

(kvsn/write-str {:a "1" :b "2"})
(kvsn/read-str "a: 1\nb:2")
```

## License

Copyright 漏 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
