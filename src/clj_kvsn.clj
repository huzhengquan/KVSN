(ns clj-kvsn
  (:use [clojure.string :only [join split split-lines blank? trim]]))

(defn- make-m-kv
  [k v]
  (str k " :\n"
    (join
      \newline
      (map #(str \tab %)
        (split-lines v)))))

(defn write-str
  [x]
  (join "\n"
    (map
      #(let [k (name (first %))
             v (str (last %))]
        (if (and (< (count v) 20)
                 (not (re-find #"\n" v)))
          (str k " : " v)
          (make-m-kv k v)))
      x)))

(defn read-str
  [x]
  (first
  (reduce
    (fn [[r pk] line]
      (let [[k v] (split line #":" 2)
            kisb? (blank? k)]
        (cond
          (and (not kisb?) (not= \tab (first k)))
            (let [kk (keyword (trim k))
                  vv (trim v)]
              [(assoc r kk vv) kk])
          (and pk (= \tab (first line)))
            (let [vv (subs line 1)]
              [(merge-with #(if (= %1 "") %2 (str %1 \newline %2))
                           r
                           {pk vv}) pk])
          :else [r pk])))
    [{} nil]
    (split-lines x))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println (write-str (read-str "a :1\nb: 2\nc :\n\t111\n\t222\nd :\n e:3")))
  (println x))
