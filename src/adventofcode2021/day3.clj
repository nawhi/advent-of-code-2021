(ns adventofcode2021.day3
  (:require [clojure.string :as string]))

(defn least-common-bit [digits]
  (let [group (group-by #(= "1" %) digits)
        ones (get group true)
        zeroes (get group false)]
    (if (> (count ones) (count zeroes)) "1" "0")))

(defn epsilon
  "Returns number with the least common bit at each position for each input number"
  [numbers]
  (let [num-length (count (first numbers))]
    (loop [i 0 res ""]
      (if (= i num-length)
        res
        (let [bit-this-pos (least-common-bit (map #(subs % i (+ i 1)) numbers))]
          (recur (+ i 1) (str res bit-this-pos)))))))
