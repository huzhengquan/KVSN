(ns clj-kvsn)

(defn- make-m-kv
  [k v]
  (str k ":\n"
    (clojure.string/join
      \newline
      (map #(str \tab %)
        (clojure.string/split-lines v)))
    "\n:" k))

(defn write-str
  [x]
  (clojure.string/join "\n"
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
  (apply hash-map
  (reduce
    (fn [r line]
      (cond
        (= \tab (first line))
          (assoc r (dec (count r)) (str (last r) (if (empty? (last r)) "" \newline) (subs line 1)))
        (clojure.string/blank? line) r
        :else
          (let [[k v] (clojure.string/split line #":" 2)]
             (if (clojure.string/blank? k)
               r
               (conj r (keyword k) v)))))
    []
    (clojure.string/split-lines x))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x))
