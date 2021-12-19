(ns adventofcode2021.day10)

(def is-open-paren (partial contains? #{\( \{ \[ \<}))
(def is-close-paren (partial contains? #{\) \} \] \>}))
(def match-paren (partial get {\[ \] \( \) \{ \} \< \>}))
(def score (partial get {\) 3 \] 57 \} 1197 \> 25137}))

(defn parse-internal [line stack]
  (println "parse-internal '" line "', stack=" stack)
  (if (empty? line) nil
  (let [[c] line expected (last stack)]
    (if (is-close-paren c)
      (if (= expected c)
        (parse-internal (subs line 1) (pop stack))
        c)
      (parse-internal (subs line 1) (conj stack (match-paren c)))))))


(defn parse [line] (parse-internal line []))
