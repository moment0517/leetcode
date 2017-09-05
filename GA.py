# -*- coding:utf-8 -*-
# import matlibplot.pyplot as plt
########################################
# 计算f(x)最大值
# f(x) = x + 10sin(5x) + 7cos(4x), x∈[0,9] 
# 使用遗传算法，大致参照文章
# http://blog.csdn.net/b2b160/article/details/4680853/
########################################

import math
import numpy as np

Population_size = 100
Max_iteration = 10

# 染色长度计算方法：根据x区间的最大值为9，为保证小数后4位，9000在2的17次方和2的16次之间。
Chromosome_length = 17

Crossover_probability = 0.7
Mutation_probability = 0.1
X_upper = 13
Precision = 4
X_upper_P = X_upper*10.0**Precision


Population = None
Population_selected = None
Fitness = None
Fitness_ratio = None


def population_init():
	global Population
	fmt = '{:0>'+str(Chromosome_length)+'}'
	Population = map(lambda x: fmt.format(bin(x)[2:]),
		(np.random.rand(Population_size) * X_upper_P).astype(np.int))
	fitness_cal()

def object_f(x):
	return (x + 10*math.sin(5*x) + 7*math.cos(4*x))

def fitness(x):
	tmp = object_f(int(x, 2)/10.0**Precision)
	return math.exp(tmp)

def fitness_cal():
	global Fitness
	global Fitness_ratio
	Fitness = np.array(map(fitness, Population))
	Fitness_ratio = Fitness / np.sum(Fitness)

def select():
	global Population_selected
	global Population
	Population_selected = list()
	fitness_cum = np.cumsum(Fitness_ratio)
	for i in range(Population_size):
		rand = np.random.rand()
		index = np.argwhere(fitness_cum>rand)[0][0]
		Population_selected.append(Population[index])
	np.random.shuffle(Population_selected)
	Population = list()

def crossover(p1, p2):
	cross_point = np.random.randint(Chromosome_length)
	rand = np.random.rand()
	if rand <= Crossover_probability:
		p1_ = p1[:cross_point] + p2[cross_point:]
		p2_ = p1[cross_point:] + p2[:cross_point]
		if int(p1_, 2) < X_upper_P:
			p1 = p1_
		if int(p2_, 2) < X_upper_P:
			p2 = p2_
	return p1, p2

def mutation(p):
	mutation_point = np.random.randint(Chromosome_length)
	rand = np.random.rand()
	if rand <= Mutation_probability:
		p_ = p[:mutation_point] + str((1-int(p[mutation_point]))) + p[(mutation_point+1):]
		if int(p_, 2) < X_upper_P:
			return p_
	return p

def iteration():
	global Population
	select()
	for i in range(Population_size/2):
		p1, p2 = Population_selected[i], Population_selected[i+1]
		p1_, p2_ = crossover(p1, p2)
		p1__, p2__ = mutation(p1_), mutation(p2_)
		Population.extend((p1__, p2__))
	fitness_cal()
	fitness_max_index = np.argmax(Fitness)
	optimal = int(Population[fitness_max_index], 2)/10.0**Precision
	print(optimal, object_f(optimal))


def main():
	population_init()
	for i in range(Max_iteration):
		print("iteration: %d" % i)
		iteration()

if __name__ == '__main__':
	main()





