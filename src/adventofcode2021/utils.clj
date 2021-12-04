(ns adventofcode2021.utils
  (:require [clojure.string :as string]))

(defn puzzle-input [puzzle-id]
  "returns lines of input for a puzzle"
  (string/split (slurp (str "resources/" puzzle-id ".txt")) #"\n"))
