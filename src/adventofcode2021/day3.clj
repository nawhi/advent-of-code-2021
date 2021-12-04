(ns adventofcode2021.day3)

(defn least-common-bit [digits]
  (let [group (group-by #(= "1" %) digits)
        ones (get group true)
        zeroes (get group false)]
    (if (> (count ones) (count zeroes)) "1" "0")))

(defn epsilon
  "Returns number with the least common bit at each position for each input number"
  [numbers]
  (least-common-bit numbers))
