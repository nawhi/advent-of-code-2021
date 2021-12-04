(ns adventofcode2021.day3
  (:require [clojure.string :as string]))

(defn least-common-bit [digits]
  (let [group (group-by #(= "1" %) digits)
        ones (get group true)
        zeroes (get group false)]
    (if (> (count ones) (count zeroes)) "1" "0")))

(defn most-common-bit [digits]
  (let [group (group-by #(= "1" %) digits)
        ones (get group true)
        zeroes (get group false)]
    (if (> (count ones) (count zeroes)) "0" "1")))

(defn- decode
  "Returns number with the least common bit at each position for each input number"
  [reducer numbers]
  (let [num-length (count (first numbers))]
    (loop [i 0 res ""]
      (if (= i num-length)
        (Integer/parseInt res 2)
        (let [bit-this-pos (reducer (map #(subs % i (+ i 1)) numbers))]
          (recur (+ i 1) (str res bit-this-pos)))))))

(def epsilon (partial decode least-common-bit))
(def gamma (partial decode most-common-bit))

(defn power-consumption [numbers]
  (* (epsilon numbers) (gamma numbers)))
