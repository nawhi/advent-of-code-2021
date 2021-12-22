(ns adventofcode2021.day14
  (:require [clojure.string :as string]))

(defn- parse-rules [rules]
  (->> rules
       (map #(string/split % #" -> "))
       (into {})))

(defn- handle-pair [[_ second :as pair] rules]
  (str (get rules (apply str pair)) second))

(defn simulate-pair-insertion [rules template]
  (let [rules (parse-rules rules)]
    (str (first template) (->> template
                               (partition 2 1)
                               (map #(handle-pair % rules))
                               (#(string/join "" %))))))

(defn score-polymer [polymer]
  (let [freqs
        (->> polymer
             (frequencies)
             (sort-by val))
        [_ count-least-common] (first freqs)
        [_ count-most-common] (last freqs)]
    (- count-most-common count-least-common)))

(defn run-simulation [[template _ & rules]]
  (score-polymer (nth (iterate (partial simulate-pair-insertion rules) template) 10)))
