(ns adventofcode2021.day14
  (:require [clojure.string :as string]))

(defn- parse-rules [rules]
  (->> rules
       (map #(string/split % #" -> "))
       (into {})))

(defn- handle-pair [[_ second :as pair] rules]
  (str (get rules (apply str pair)) second))

(defn simulate-pair-insertion [template rules]
  (let [rules (parse-rules rules)]
    (str (first template) (->> template
                               (partition 2 1)
                               (map #(handle-pair % rules))
                               (#(string/join "" %))))))
