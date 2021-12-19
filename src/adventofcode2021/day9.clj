(ns adventofcode2021.day9)

(defn get2d
  ([arr2d x y] (try
                 (nth (nth arr2d y) x)
                 (catch Exception _ nil)))
  ([arr2d x y default] (or (get2d arr2d x y) default)))

(def NEIGHBOURS [[0 1]
                 [-1 0] [1 0]
                 [0 -1]])

(defn find-local-minima [heightmap]
  (let [height (range 0 (count heightmap))
        width (range 0 (count (first heightmap)))]
    (->> (for [x width y height]
           (let [entry (get2d heightmap x y)
                 score (for [[i j] NEIGHBOURS]
                         (> (get2d heightmap (+ x i) (+ y j) ##Inf) entry))]
             (if (every? true? score) entry)))
         (filter some?)
         (sort <))))

(defn risk-level [minima]
  (->> minima
       (map (partial + 1))
       (reduce +)))
