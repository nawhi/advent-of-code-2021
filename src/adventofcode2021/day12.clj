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
    (map #(find-routes-from graph %1 (conj route %1)) (get graph node))))

(defn find-routes [graph]
  (set (find-routes-from graph "start" ["start"])))
