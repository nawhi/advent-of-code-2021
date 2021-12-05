(ns adventofcode2021.utils
  (:require [clojure.string :as string]))


(defn raw-puzzle-input [puzzle-id]
  "returns input for a puzzle as one string"
  (slurp (str "resources/" puzzle-id ".txt")))

(defn puzzle-input [puzzle-id]
  "returns lines of string input for a puzzle"
  (string/split (raw-puzzle-input puzzle-id) #"\n"))
