(ns adventofcode2021.day12
  (:require [clojure.string :as string]))

(defn parse-cave [lines]
  (reduce (fn [caves line]
            (let [[from to] (string/split line #"-")]
              (update caves from #(set (conj % to)))))
          {}
          lines))

(defn routes- [graph node route]
  (if (= node "end")
    route
    (for [child (get graph node)]
      (routes- graph child (conj route child)))))

(defn routes [graph]
  (set (routes- graph "start" ["start"])))
