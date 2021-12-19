(ns adventofcode2021.day10)

(def close? (partial contains? #{\) \} \] \>}))
(def match (partial get {\[ \] \( \) \{ \} \< \>}))
(def score (partial get {\) 3 \] 57 \} 1197 \> 25137}))

(defn- parse-internal [line stack]
  (let [c (first line) expected (last stack)]
    (cond
      (nil? c) nil
      (close? c) (if (= expected c)
                   (parse-internal (subs line 1) (pop stack))
                   c)
      :else (parse-internal (subs line 1) (conj stack (match c))))))

(defn parse [line] (parse-internal line []))

(defn syntax-score [lines]
  (->> lines
       (map #(parse %))
       (map score)
       (filter some?)
       (reduce +)))
