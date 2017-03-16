from unittest import TestCase

from alg_cluster import Cluster
from alg_project3_solution import slow_closest_pairs
from alg_project3_solution import fast_closest_pair
from alg_project3_solution import hierarchical_clustering
from alg_project3_solution import kmeans_clustering


class TestHierClustering(TestCase):
    def test_one_pair(self):
        s = map(lambda x: Cluster(*x),
                [(set([5]), 0, 0, 13, 0.1),
                 (set([10]), 42, 0, 14, 0.2)])

        r = reduce(lambda acc, x: [acc, acc.update(x.fips_codes())][0],
                   hierarchical_clustering(s, 1), set())
        self.assertEqual(set([5, 10]), r)

    def test_four_pairs(self):
        s = map(lambda x: Cluster(*x),
                [(set([1]), 0, 0, 13, 0.1),
                 (set([2]), 0, 0, 14, 0.2),
                 (set([3]), 0, 0, 14, 0.2),
                 (set([4]), 0, 0, 14, 0.2),

                 (set([10]), 10, 10, 14, 0.2),
                 (set([11]), 10, 10, 14, 0.2),
                 (set([12]), 10, 10, 14, 0.2),
                 (set([13]), 10, 10, 14, 0.2),
                 ])
        # s = map(lambda x: Cluster(*x),
        #         [(set([1]), 0, 0, 13, 0.1),
        #          (set([2]), 1, 0, 14, 0.2),
        #          (set([3]), 2, 0, 14, 0.2),
        #          (set([4]), 3, 0, 14, 0.2),

        #          (set([10]), 0, 100, 14, 0.2),
        #          (set([11]), 1, 100, 14, 0.2),
        #          (set([12]), 2, 100, 14, 0.2),
        #          (set([13]), 3, 100, 14, 0.2),
        #          ])

        h = reduce(lambda acc, x: [acc, acc.append(x.fips_codes())][0],
                   hierarchical_clustering(s, 2), [])
        k = reduce(lambda acc, x: [acc, acc.append(x.fips_codes())][0],
                   kmeans_clustering(s, 2, 10), [])

        h = sorted([list(x) for x in h])
        k = sorted([list(x) for x in k])

        # print(h)
        # print(k)
        self.assertEqual(h, k)


class TestClosestPairs(TestCase):

    def test_owl1(self):
        s = [Cluster(set(['01101']), 720.281573781, 440.436162917, 223510, 5.7e-05),
             Cluster(set(['01117']), 709.193528999, 417.394467797, 143293, 5.6e-05),
             Cluster(set(['01073']), 704.191210749, 411.014665198, 662047, 7.3e-05),
             Cluster(set(['01015']), 723.907941153, 403.837487318, 112249, 5.6e-05),
             Cluster(set(['01113']), 740.385154867, 436.939588695, 49756, 5.6e-05),
             Cluster(set(['04013']), 214.128077618, 396.893960776, 3072149, 6.8e-05),
             Cluster(set(['06025']), 156.397958859, 393.161127277, 142361, 5.6e-05),
             Cluster(set(['06065']), 146.410389633, 374.21707964, 1545387, 6.1e-05),
             Cluster(set(['06073']), 129.2075529, 387.064888184, 2813833, 6.6e-05),
             Cluster(set(['06059']), 113.997715586, 368.503452566, 2846289, 9.8e-05),
             Cluster(set(['06037']), 105.369854549, 359.050126004, 9519338, 0.00011),
             Cluster(set(['06111']), 93.4973310868, 344.590570899, 753197, 5.8e-05),
             Cluster(set(['06083']), 76.0382837186, 340.420376302, 399347, 6.4e-05),
             Cluster(set(['06029']), 103.787886113, 326.006585349, 661645, 9.7e-05),
             Cluster(set(['06071']), 148.402461892, 350.061039619, 1709434, 7.7e-05),
             Cluster(set(['06107']), 108.085024898, 306.351832438, 368021, 5.8e-05),
             Cluster(set(['06039']), 97.2145136451, 278.975077449, 123109, 6e-05),
             Cluster(set(['06019']), 95.6093812211, 290.162708843, 799407, 6.4e-05),
             Cluster(set(['06081']), 52.6171444847, 262.707477827, 707161, 5.6e-05),
             Cluster(set(['06001']), 61.782098866, 259.312457296, 1443741, 7e-05),
             Cluster(set(['06085']), 63.1509653633, 270.516712105, 1682585, 6.3e-05),
             Cluster(set(['06067']), 74.3547338322, 245.49501455, 1223499, 6.1e-05),
             Cluster(set(['06075']), 52.7404001225, 254.517429395, 776733, 8.4e-05),
             Cluster(set(['06113']), 68.2602083189, 236.862609218, 168660, 5.9e-05),
             Cluster(set(['06101']), 74.2003718491, 229.646592975, 78930, 5.6e-05),
             Cluster(set(['06021']), 65.2043358182, 213.245337355, 26453, 6.9e-05),
             Cluster(set(['06089']), 77.359494209, 188.945068958, 163256, 5.7e-05),
             Cluster(set(['08005']), 380.281283151, 270.268826873, 487967, 5.9e-05),
             Cluster(set(['08001']), 379.950978294, 265.078784954, 363857, 6.6e-05),
             Cluster(set(['08031']), 371.038986573, 266.847932979, 554636, 7.9e-05),
             Cluster(set(['09003']), 925.917212741, 177.152290276, 857183, 5.7e-05),
             Cluster(set(['12073']), 762.463896365, 477.365342219, 239452, 6.1e-05),
             Cluster(set(['13313']), 737.308367745, 378.040993858, 83525, 5.6e-05),
             Cluster(set(['13215']), 745.265661102, 430.987078939, 186291, 5.9e-05),
             Cluster(set(['13067', '13121']), 748.913132176, 398.791948963, 1423757, 6.74388143482e-05),
             Cluster(set(['13063', '13151']), 754.106675876, 406.912690943, 355858, 6.2646387042e-05),
             Cluster(set(['13089', '13247']), 754.838224932, 400.291739409, 735976, 6.68568485929e-05),
             Cluster(set(['13135']), 758.038826857, 395.110327675, 588448, 6.3e-05),
             Cluster(set(['13245']), 796.799727342, 404.391349655, 199775, 5.9e-05),
             Cluster(set(['19163']), 621.490118929, 227.666851619, 158668, 5.6e-05),
             Cluster(set(['17031']), 668.978975824, 219.400257219, 5376741, 6.1e-05),
             Cluster(set(['21019']), 768.726553092, 290.270551648, 49752, 5.8e-05),
             Cluster(set(['21111']), 715.347723878, 301.167740487, 693604, 5.9e-05),
             Cluster(set(['22017']), 570.826412839, 442.202574191, 252161, 6.2e-05),
             Cluster(set(['22071']), 651.338581076, 496.465402252, 484674, 6.4e-05),
             Cluster(set(['25017']), 943.405755498, 156.504310828, 1465396, 5.6e-05),
             Cluster(set(['25025']), 950.299079197, 158.007070966, 689807, 7e-05),
             Cluster(set(['24510', '24005']), 872.39640107, 248.277002427, 1405446, 6.70230005279e-05),
             Cluster(set(['24027']), 867.127763298, 252.141340019, 247842, 6e-05),
             Cluster(set(['24031']), 863.180208628, 255.65657011, 873341, 6.5e-05),
             Cluster(set(['26163']), 746.37046732, 200.570021537, 2061162, 6.4e-05),
             Cluster(set(['26125']), 743.036942153, 192.129690868, 1194156, 5.7e-05),
             Cluster(set(['27123']), 576.516685202, 151.219277482, 511035, 5.6e-05),
             Cluster(set(['27053']), 570.131597541, 151.403325043, 1116200, 5.8e-05),
             Cluster(set(['29189', '29510']), 629.976164517, 297.473005985, 1364504, 6.22965861588e-05),
             Cluster(set(['28027']), 631.700027283, 400.68741948, 30622, 6e-05),
             Cluster(set(['28159']), 663.514261498, 425.274137823, 20160, 5.9e-05),
             Cluster(set(['28049']), 638.051593606, 445.785870317, 250800, 6e-05),
             Cluster(set(['37119']), 813.724315147, 356.853362811, 695454, 5.6e-05),
             Cluster(set(['31055']), 525.799353573, 238.14275337, 463585, 6.2e-05),
             Cluster(set(['31109']), 516.78216337, 250.188023316, 250291, 6.1e-05),
             Cluster(set(['36005', '34013', '34017', '36061', '36081', '36047', '36085', '34039']), 911.004570517, 207.361438421, 9933427, 0.000100484812543),
             Cluster(set(['34003', '34031']), 906.566100671, 202.092249843, 1373167, 6.68631193438e-05),
             Cluster(set(['34023']), 904.976453741, 215.001458637, 750162, 5.9e-05),
             Cluster(set(['34007']), 899.061431482, 232.054232622, 508932, 5.7e-05),
             Cluster(set(['36059']), 917.384980291, 205.43647538, 1334544, 7.6e-05),
             Cluster(set(['36103']), 929.241649488, 199.278463003, 1419369, 6.3e-05),
             Cluster(set(['36119']), 912.141547823, 196.592589736, 923459, 6.5e-05),
             Cluster(set(['39035']), 776.351457758, 216.558042612, 1393978, 5.8e-05),
             Cluster(set(['41005']), 103.421444616, 88.318590492, 338391, 6.6e-05),
             Cluster(set(['41051']), 103.293707198, 79.5194104381, 660486, 9.3e-05),
             Cluster(set(['41067']), 92.2254623376, 76.2593957841, 445342, 7.3e-05),
             Cluster(set(['42101']), 894.72914873, 227.900547575, 1517550, 5.8e-05),
             Cluster(set(['42003']), 809.003419092, 233.899638663, 1281666, 6.1e-05),
             Cluster(set(['47037']), 700.009323976, 350.107265446, 569891, 6.1e-05),
             Cluster(set(['47065']), 732.643747577, 370.017730905, 307896, 6.1e-05),
             Cluster(set(['47093']), 753.012743594, 348.235180569, 382032, 5.6e-05),
             Cluster(set(['48201']), 540.54731652, 504.62993865, 3400578, 6e-05),
             Cluster(set(['48245']), 565.746895809, 504.541799993, 252051, 5.7e-05),
             Cluster(set(['51520']), 784.05333332, 328.847863787, 17367, 5.6e-05),
             Cluster(set(['51610', '51059']), 863.075130421, 262.406379235, 980126, 5.71270489713e-05),
             Cluster(set(['51510', '11001', '51013', '24033']), 868.773385157, 261.367545904, 1691310, 7.01566407105e-05),
             Cluster(set(['51775', '51770']), 821.539811344, 307.579291084, 119658, 6.35522990523e-05),
             Cluster(set(['51680']), 835.264653899, 302.326633095, 65269, 5.8e-05),
             Cluster(set(['51820']), 837.346467474, 285.851438947, 19520, 5.8e-05),
             Cluster(set(['51570']), 868.048530719, 299.360459202, 16897, 5.6e-05),
             Cluster(set(['51840']), 845.843602685, 258.214178983, 23585, 7.1e-05),
             Cluster(set(['51087', '51760']), 865.974521771, 293.191364776, 460090, 7.28875654763e-05),
             Cluster(set(['53011']), 104.00046468, 74.0182527309, 345238, 6.4e-05),
             Cluster(set(['53033']), 125.27486023, 39.1497730391, 1737034, 5.8e-05),
             Cluster(set(['55079']), 664.855000617, 192.484141264, 940164, 7.4e-05),
             Cluster(set(['54009']), 799.221537984, 240.153315109, 25447, 7.7e-05)]

        # with open('owl1.csv', 'w') as f:
        #     f.write('n,x,y\n')
        #     f.writelines('%d,%s,%s\n' % (i, x.horiz_center(), x.vert_center())
        #                  for i, x in enumerate(s))
        expected = 5.2005419351770561, 27, 28

        self.assertEqual(fast_closest_pair(s), expected)
        #received (5.2858329963508002, 48, 49)

    def test_special(self):
        s = [Cluster(set([1, 2, 3, 4]), 0.0, 0.0, 55, 0.176363636364),
             Cluster(set([10, 11]), 10.0, 10.0, 28, 0.2),
             Cluster(set([12]), 10, 10, 14, 0.2),
             Cluster(set([13]), 10, 10, 14, 0.2)
             ]
        # print(s)
        d, i, j = fast_closest_pair(s)
        # print(d, i, j)
        # print(s[i], s[j])
        self.assertNotEqual((i, j), (0, 1))

    def test_one_pair(self):
        # flip, x, y, population, risk
        s = map(lambda x: Cluster(*x),
                [(5, 0, 0, 13, 0.1),
                 (10, 42, 0, 14, 0.2)])
        r1 = list(slow_closest_pairs(s))[0]
        r2 = fast_closest_pair(s)
        self.assertEqual(r1, r2)
        self.assertEqual(r2, (42.0, 0, 1))

    def test_three_horiz_pairs(self):
        s = map(lambda x: Cluster(*x),
                [(5, 0, 0, 13, 0.1),
                 (10, 2, 0, 14, 0.2),
                 (15, 4, 0, 15, 0.2),
                 (20, 5, 0, 16, 0.2),
                 (25, 7, 0, 17, 0.2),
                 (30, 9, 0, 18, 0.2),
                 ])
        r1 = list(slow_closest_pairs(s))[0]
        r2 = fast_closest_pair(s)
        self.assertEqual(r1, r2)
        self.assertEqual(r1, (1.0, 2, 3))

    def test_three_vert_pairs(self):
        s = map(lambda x: Cluster(*x),
                [(5,  0, 0, 13, 0.1),
                 (10, 0, 2, 14, 0.2),
                 (15, 0, 4, 15, 0.2),
                 (20, 0, 5, 16, 0.2),
                 (25, 0, 7, 17, 0.2),
                 (30, 0, 9, 18, 0.2),
                 ])
        r1 = list(slow_closest_pairs(s))[0]
        r2 = fast_closest_pair(s)
        self.assertEqual(r1, r2)
        self.assertEqual(r1, (1.0, 2, 3))
