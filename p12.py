#My Name: Ryan Potocki

import random

def generate_pokemon(n,p):
    """ generates random pokemon based on a list of names, powers, and levels"""
    pokemon = {}
    pokemon['name'] = random.choice(n)
    pokemon['power'] = random.choice(power_list)
    pokemon['level'] = random.randint(1,10)
    return pokemon

class Ditto(object):
    """ a class that creates the object Ditto """
    def __init__(self):
      self.level = [1]
      self.count = 0
      self.powers = ['absorb']
	#method gets ditto's current level
    def get_level(self):
      ditto_level = sum(self.level)/float(len(self.level))
      return ditto_level
	#method tells ditto whether it should absorb a pokemon or not
    def should_absorb(self,pokemon):
      ditto_level = ditto.get_level()
      poke_level = pokemon['level']
      if (ditto_level + poke_level)/(len(self.level)) > ditto_level:
          return True
      else:
          return False
	#if the should_absorb method returns true this runs and updates the ditto object
    def absorb(self,pokemon):
      self.count += 1
      self.level.append(pokemon['level'])
      self.powers.append(pokemon['power'])

def take_walk(poke_list):
    """ runs through a list of pokemon and uses the ditto object (and methods) to change the ditto object"""
    for poke in poke_list:
        if ditto.should_absorb(poke) == True:
            ditto.absorb(poke)
        elif ditto.should_absorb(poke) == False and poke['power'] not in ditto.powers:
            ditto.absorb(poke)
    return ditto


