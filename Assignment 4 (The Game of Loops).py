def has_unique_letters(pw_1):
  i = 0
  while i < len(pw_1):
    k = 1
    while k < len(pw_1) and pw_1[i] not in ("0","1","2","3","4","5","6","7","8","9"):
      if pw_1[i] == pw_1[k] and i != k:
        return False
      k = k + 1
    i = i + 1
  return True

def has_even_vowels(pw_2):
  vowels = 0
  i = 0
  while i < len(pw_2):
    if pw_2[i] in ("a","A","e","E","I","i","O","o","U","u"):
      vowels = vowels + 1
    i = i + 1
  if (vowels%2) == 0 and vowels >= 1:
    return True
  else:
    return False

def has_special_character(pw_3):
  count = 0
  i = 0
  while i < len(pw_3):
    if pw_3[i] in ("@","#","$","*"):
      count = count + 1
    i = i + 1
  if count >= 1:
    return True
  else:
    return False

def has_divisible_numbers(pw_4):
  count = 0
  i = 0
  while i < len(pw_4):
    if pw_4[i] in ("0","1","2","3","4","5","6","7","8","9") and pw_4[i] in ("0","1","5","7"):
        count = count + 1
    i = i + 1
  if count > 0:
      return False
  else:
    return True

def check_password(pw_5):
  d = True
  if has_unique_letters(pw_5) == False:
    d = False
    print "Warning! Please ensure letters are not repeated."
  if has_even_vowels(pw_5) == False:
    d = False
    print "Warning! Please ensure password contains an even number of vowels"
  if has_special_character(pw_5) == False:
    d = False
    print "Warning! Please ensure password contains at least one of {@, #, *, $}"
  if has_divisible_numbers(pw_5) == False:
    d = False
    print "Warning! Please ensure all numbers are divisible by 2 or 3."
  if d == True:
    print "Congratulations, your password meets our criteria."
  else:
    quit()


if __name__ == "__main__":
  pw_5 = raw_input("Welcome to the Iron Bank of Bravos. Please enter your password:")
  check_password(pw_5)
