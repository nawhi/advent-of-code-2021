import numpy as np
import unittest
import itertools


def exists(mx, x, y):
    return 0 <= x < len(mx) and 0 <= y < len(mx[x])


def neighbours(x, y):
    return [[x - 1, y + 1], [x, y + 1], [x + 1, y + 1],
            [x - 1, y], [x + 1, y],
            [x - 1, y - 1], [x, y - 1], [x + 1, y - 1]]


def indices_above_nine(mx):
    return list(zip(*(mx > 9).nonzero()))


def iterate(mx):
    iterated = mx + 1
    flashes = 0
    while len(indices_above_nine(iterated)) > 0:
        for x, y in indices_above_nine(iterated):
            for xn, yn in neighbours(x, y):
                if exists(mx, xn, yn) and iterated[xn, yn] != 0:
                    iterated[xn, yn] += 1

            flashes += 1
            iterated[x, y] = 0
    return {'result': iterated, 'flashes': flashes}



def count_flashes(mx, num_iterations):
    first_full_flash = None
    num_flashes = 0
    for i in itertools.count():
        iterated = iterate(mx)
        mx = iterated['result']
        if i < 100:
            num_flashes += iterated['flashes']
        if np.all(mx == 0):
            first_full_flash = i + 1
            break
    return {'flashes': num_flashes, 'first_full_flash': first_full_flash}


class Day11(unittest.TestCase):
    def test_exists(self):
        self.assertTrue(exists(np.array([[0, 0], [0, 0]]), 0, 0))
        self.assertTrue(exists(np.array([[0, 0], [0, 0]]), 0, 1))
        self.assertTrue(exists(np.array([[0, 0], [0, 0]]), 1, 0))
        self.assertTrue(exists(np.array([[0, 0], [0, 0]]), 1, 1))
        self.assertFalse(exists(np.array([[0, 0], [0, 0]]), 2, 1))
        self.assertFalse(exists(np.array([[0, 0], [0, 0]]), 1, 2))
        self.assertFalse(exists(np.array([[0, 0], [0, 0]]), 0, -1))
        self.assertFalse(exists(np.array([[0, 0], [0, 0]]), -1, 0))

    def test_iterates(self):
        cases = [
            [[[1, 2], [3, 4]], [[2, 3], [4, 5]]],
            [[[9, 1], [1, 1]], [[0, 3], [3, 3]]],
            [[[9, 9], [1, 1]], [[0, 0], [4, 4]]],

            [[[1, 1, 1, 1, 1],
              [1, 9, 9, 9, 1],
              [1, 9, 1, 9, 1],
              [1, 9, 9, 9, 1],
              [1, 1, 1, 1, 1]], [[3, 4, 5, 4, 3],
                                 [4, 0, 0, 0, 4],
                                 [5, 0, 0, 0, 5],
                                 [4, 0, 0, 0, 4],
                                 [3, 4, 5, 4, 3]]],
            [[[3, 4, 5, 4, 3],
              [4, 0, 0, 0, 4],
              [5, 0, 0, 0, 5],
              [4, 0, 0, 0, 4],
              [3, 4, 5, 4, 3]], [[4, 5, 6, 5, 4],
                                 [5, 1, 1, 1, 5],
                                 [6, 1, 1, 1, 6],
                                 [5, 1, 1, 1, 5],
                                 [4, 5, 6, 5, 4]]],
        ]
        for input, expected in cases:
            with self.subTest():
                np.testing.assert_equal(iterate(np.array(input))['result'], np.array(expected))

    def test_count_flashes(self):
        with self.subTest("example"):
            example = np.array([[5, 4, 8, 3, 1, 4, 3, 2, 2, 3],
                                [2, 7, 4, 5, 8, 5, 4, 7, 1, 1],
                                [5, 2, 6, 4, 5, 5, 6, 1, 7, 3],
                                [6, 1, 4, 1, 3, 3, 6, 1, 4, 6],
                                [6, 3, 5, 7, 3, 8, 5, 4, 7, 8],
                                [4, 1, 6, 7, 5, 2, 4, 6, 4, 5],
                                [2, 1, 7, 6, 8, 4, 1, 7, 2, 1],
                                [6, 8, 8, 2, 8, 8, 1, 1, 3, 4],
                                [4, 8, 4, 6, 8, 4, 8, 5, 5, 4],
                                [5, 2, 8, 3, 7, 5, 1, 5, 2, 6]])
            self.assertEqual(count_flashes(example, 100)['flashes'], 1656)
            self.assertEqual(count_flashes(example, 100)['first_full_flash'], 195)
        with self.subTest("puzzle 1"):
            puzzle1 = np.array([[3, 1, 1, 3, 2, 8, 4, 8, 8, 6],
                                [2, 8, 5, 1, 8, 7, 6, 1, 4, 4],
                                [2, 7, 7, 4, 6, 6, 4, 4, 8, 4],
                                [6, 7, 1, 5, 1, 1, 2, 5, 7, 8],
                                [7, 1, 4, 6, 2, 7, 2, 1, 5, 3],
                                [6, 2, 5, 6, 6, 5, 6, 3, 6, 7],
                                [3, 1, 4, 8, 6, 6, 6, 2, 4, 5],
                                [3, 8, 5, 7, 4, 4, 6, 5, 2, 8],
                                [7, 3, 2, 2, 4, 2, 2, 8, 3, 3],
                                [8, 1, 5, 2, 1, 7, 5, 1, 6, 8]])
            self.assertEqual(count_flashes(puzzle1, 100)['flashes'], 1705)
            self.assertEqual(count_flashes(puzzle1, 100)['first_full_flash'], 265)

    def test_thing(self):
        self.assertTrue(np.all(np.array([[0, 0], [0, 0]]) == 0))


if __name__ == "__main__":
    unittest.main()
