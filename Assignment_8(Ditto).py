import random
random.seed(0)

names = ['Charzard', 'Mew', 'Squirtle']
powers = ['Strike', 'Pounce','Smash']



def generate_pokemon(n,p):
    ditto = {}
    ditto['Name'] = random.choice(n)
    ditto['Power'] = random.choice(p)
    ditto['Level'] = random.int(1,10)
    print ditto


def get_level(d):
    pass

def should_absorb(d,p):
    pass

def take_walk(p):
    pass

generate_pokemon(names,powers)
