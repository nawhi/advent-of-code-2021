(ns adventofcode2021.day7)

(defn fuel-for-position [crabs position]
  (for [crab crabs] (Math/abs (- crab position))))

(defn fuel-for-best-align-position [crabs]
  "I am a  v e r y   s l o w  solution"
  (let [positions (range (apply min crabs) (+ (apply max crabs) 1))
        scores (map (fn [pos] {:pos pos :score (reduce + (fuel-for-position crabs pos))}) positions)]
    (:score (apply min-key :score scores))))
