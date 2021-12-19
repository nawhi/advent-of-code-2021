(ns adventofcode2021.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day10 :refer :all]))

(deftest syntax-score-test
  (are [string expected]
    (= (parse string) expected)
    ")" \)
    "]" \]
    "}" \}
    ">" \>
    "()" nil
    "(())" nil
    "{}" nil
    "{}{}{}" nil
    "{{{{{}}}}}" nil
    "[]" nil
    "<>" nil
    "[)" \)
    "(]" \]
    "[}" \}
    "{>" \>
    "(()]" \]
    "({<})]" \}
    "({<})]" \}
    "{([(<{}[<>[]}>{[]{[(<()>" \}
    "[[<[([]))<([[{}[[()]]]" \)
    "[{[{({}]{}}([{[{{{}}([]" \]
    "[<(<(<(<{}))><([]([]()" \)
    "<{([([[(<>()){}]>(<<{{" \>
    ))
