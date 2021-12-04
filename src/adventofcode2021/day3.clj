(ns adventofcode2021.day3)

(defn more-ones-than-zeros [digits]
  (let [group (group-by #(= "1" %) digits)]
    (> (count (get group true)) (count (get group false)))))

; gets correct answer,
; but these are the wrong way round.
; Wtf
(defn least-common-bit [digits]
  (if (more-ones-than-zeros digits) "1" "0"))

(defn most-common-bit [digits]
  (if (more-ones-than-zeros digits) "0" "1"))

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
