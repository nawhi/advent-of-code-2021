(ns adventofcode2021.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.utils :refer :all]
            [adventofcode2021.day10 :refer :all]))

(deftest parse-test
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

(deftest syntax-score-test
  (testing "example from puzzle description"
    (let [lines ["[({(<(())[]>[[{[]{<()<>>"
                 "[(()[<>])]({[<{<<[]>>("
                 "{([(<{}[<>[]}>{[]{[(<()>"
                 "(((({<>}<{<{<>}{[]{[]{}"
                 "[[<[([]))<([[{}[[()]]]"
                 "[{[{({}]{}}([{[{{{}}([]"
                 "{<[[]]>}<{[{[{[]{()[[[]"
                 "[<(<(<(<{}))><([]([]()"
                 "<{([([[(<>()){}]>(<<{{"
                 "<{([{{}}[<[[[<>{}]]]>[]]"]]
      (is (= 26397 (syntax-score lines)))))
  (testing "puzzle 1"
    (is (= 318099 (syntax-score (puzzle-input "10.1")))))
  )
