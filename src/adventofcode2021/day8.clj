(ns adventofcode2021.day8
  (:require [clojure.string :as string]))

(defn- debug [x] (println x) x)

(defn frequency-of-1478 [lines]
  "returns number of times a 2, 3, 4 or 7-character word appeared after the bar"
  (println (string/join ", " lines))
  (->> lines
       (map #(last (string/split % #" \| ")))
       (map #(string/split % #" "))
       (flatten)
       (filter #(contains? #{2 3 4 7} (count %)))
       (count)))
