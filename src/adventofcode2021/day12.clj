(ns adventofcode2021.day12
  (:require [clojure.string :as string]))

(defn parse-cave [lines]
  (reduce (fn [caves line]
            (let [[from to] (string/split line #"-")]
              (update caves from #(set (conj % to)))))
          {}
          lines))
