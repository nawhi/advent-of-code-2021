(ns adventofcode2021.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.utils :refer :all]
            [adventofcode2021.day10 :refer :all]))

(deftest parse-test
  (are [string expected]
    (is (= (parse string) expected))
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

(deftest autocomplete-test
  (testing "autocomplete"
    (are [input expected]
    (is (= (autocomplete input) expected))
    "(]" nil
    "()" ""
    "((" "))"
    "(({<" ">}))"
    "[({(<(())[]>[[{[]{<()<>>" "}}]])})]"
    "[({(<(())[]>[[{[]{<()<>>" "}}]])})]"
    "[(()[<>])]({[<{<<[]>>(" ")}>]})"
    "(((({<>}<{<{<>}{[]{[]{}" "}}>}>))))"
    "{<[[]]>}<{[{[{[]{()[[[]" "]]}}]}]}>"
    "<{([{{}}[<[[[<>{}]]]>[]]" "])}>"
    ))
  (testing "score autocomplete string"
    (are [input expected]
      (is (= (score-autocomplete-string input) expected))
      ")" 1
      "]" 2
      "}" 3
      ">" 4
      "))" 6
      "])}>" 294
      "}}]])})]" 288957
      "}}>}>))))" 1480781
      )))


(def PUZZLE-EXAMPLE ["[({(<(())[]>[[{[]{<()<>>"
                     "[(()[<>])]({[<{<<[]>>("
                     "{([(<{}[<>[]}>{[]{[(<()>"
                     "(((({<>}<{<{<>}{[]{[]{}"
                     "[[<[([]))<([[{}[[()]]]"
                     "[{[{({}]{}}([{[{{{}}([]"
                     "{<[[]]>}<{[{[{[]{()[[[]"
                     "[<(<(<(<{}))><([]([]()"
                     "<{([([[(<>()){}]>(<<{{"
                     "<{([{{}}[<[[[<>{}]]]>[]]"])

(deftest syntax-score-test
  (testing "example from puzzle description"
    (is (= 26397 (syntax-score PUZZLE-EXAMPLE))))
  (testing "puzzle 1"
    (is (= 318099 (syntax-score (puzzle-input "10.1")))))
  (testing "example from puzzle 2"
    (is (= 288957 (autocomplete-score PUZZLE-EXAMPLE))))
  (testing "puzzle 2"
    (is (= 2389738699 (autocomplete-score (puzzle-input "10.1"))))))
