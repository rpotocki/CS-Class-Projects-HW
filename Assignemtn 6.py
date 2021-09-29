import random

def get_level(shots):
	if shots in (0,1,2):
		level = 0
	if shots in (3,4,5):
		level = 1
	if shots in (6,7,8):
		level = 2
	if shots in (9,10,11):
		level = 3
	if shots >= 12:
		level = 4
	return level 
	
def get_walk(level):
	walk_list = []
	step_length = random.randint(1,(5-level))
	walk_list.append(step_length)
	steps_forward = random.randint(4,6)
	walk_list.append(steps_forward)
	steps_backwards = steps_forward - random.randint(1,3)
	walk_list.append(steps_backwards)
	return walk_list

def update():
	position = []
	
def run_simulation():
	seed = int(raw_input("Enter random seed value:"))
	shots = int(raw_input("How many shots did Hobbes take?"))
	distance = int(raw_input("How far is it to Hobbes' home?"))
	seed = random.seed(seed)
	pothole = random.randint(1,distance-1)
	print "Hole Position is", pothole
	#call get level with number of shots to get drunk level
	level = get_level(shots)
	print "Level is", level
	#call get walk with the drunk level from above to get the step length and pattern and display them
	walk_list = get_walk(level)
	print "Step length is", walk_list[0]
	print "Step pattern is",walk_list[1],"forward and",walk_list[2],"backward"
	#update() function which updates hobbes position and append every new position value to a list that you will returtn at the end of the function
	#return appended list