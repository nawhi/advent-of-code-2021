(ns adventofcode2021.day12
  (:require [clojure.string :as string]))

(defn parse-cave [lines]
  (reduce (fn [caves line]
            (let [[from to] (string/split line #"-")]
              (update caves from #(set (conj % to)))))
          {}
          lines))

(defn find-routes-from [graph node route]
  (if (= node "end")
    route
    (for [child (get graph node)]
      (find-routes-from graph child (conj route child)))))

(defn find-routes [graph]
  (set (find-routes-from graph "start" ["start"])))
