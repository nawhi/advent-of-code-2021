(ns adventofcode2021.utils
  (:require [clojure.string :as string]))


(defn raw-puzzle-input [puzzle-id]
  "returns input for a puzzle as one string"
  (slurp (str "resources/" puzzle-id ".txt")))

(defn puzzle-input [puzzle-id]
  "returns lines of string input for a puzzle"
  (string/split (raw-puzzle-input puzzle-id) #"\n"))

(defn get2d
  "Get a cell from a 2d collection. Returns nil or a default
  if one or both indices are out of bounds"
  ([coll x y] (try
                (nth (nth coll y) x)
                (catch Exception _ nil)))
  ([arr2d x y default] (or (get2d arr2d x y) default)))
