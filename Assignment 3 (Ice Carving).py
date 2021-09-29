"""
# My Name: Ryan Potocki

def cube_vol(side):
	return side**3

def pyramid_vol(side,height):
	return (side*side*(height-side))/3.0

def ice_to_water(side,height):
  amount = 0.92*((pyramid_vol(side,height)+cube_vol(side)))
  print "A sculpture with the sides",side,"m and total height of",height,"m requires:"
  print amount
  print "cubic meters of water"
  
ice_to_water(1.6,2)
"""

# My Name: Ryan Potocki

def cube_vol(side):
	return side**3

def pyramid_vol(side,height):
	return (side*side*(height-side))/3.0

volume = pyramid_vol(1.6,2)+cube_vol(1.6)

def ice_to_water(volume):
  amount = 0.92*volume
  return amount

print "A sculpture with the sides 1.6 m and total height 2 m requires:"
print ice_to_water(volume)
print "cubic meters of water"
