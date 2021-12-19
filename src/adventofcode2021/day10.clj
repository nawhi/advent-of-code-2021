(ns adventofcode2021.day10
  (:require [clojure.string :as string]))

(def close? (partial contains? #{\) \} \] \>}))
(def match (partial get {\[ \] \( \) \{ \} \< \>}))
(def score (partial get {\) 3 \] 57 \} 1197 \> 25137}))

(defn- parse-internal [line stack]
  (loop [line line stack stack]
    (let [[c] line expected (peek stack)]
      (cond
        (nil? c) stack
        (close? c) (if (not (= expected c))
                     c
                     (recur (subs line 1) (pop stack)))
        :else (recur (subs line 1) (conj stack (match c)))))))

(defn parse [line]
  (let [result (parse-internal line [])]
    (if (vector? result) nil result)))

(defn syntax-score [lines]
  (->> lines
       (map #(parse %))
       (map score)
       (filter some?)
       (reduce +)))

(defn autocomplete [line]
  (let [result (parse-internal line [])]
    (if (char? result)
      nil
      (string/join "" (reverse result)))))
