(ns adventofcode2021.day12
  (:require [clojure.string :as string]))

(defn parse-cave [lines]
  (reduce (fn [caves line]
            (let [[node1 node2] (string/split line #"-")]
              (-> caves
                  (update node1 #(set (conj % node2)))
                  (update node2 #(set (conj % node1))))))
          {}
          lines))

(defn big-cave?
  "Big caves are all upper case and can be visited more than once"
  [node]
  (= (string/upper-case node) node))

(defn debug [x] (println "[debug]" x) x)

(defn already-visited [route node]
  (boolean (some #{node} route)))

(defn visitable-children
  "A child is visitable if it is either a big cave or has not been visited before"
  [graph node route]
  (->> (get graph node)
       (filter #(or (big-cave? %) (not (already-visited route %))))))

(defn routes- [graph node route]
  (if (= node "end")
    (string/join "," route)
    (let [children (visitable-children graph node route)]
      (for [child children] (routes- graph child (conj route child)))
      )))

(defn routes [lines]
  (let [cave (parse-cave lines)]
    (println cave)
    (set (flatten (routes- cave "start" ["start"])))))
