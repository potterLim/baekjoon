def easieat_problem_number_in_the_year_at_ucpc(year):
    problem_number = "A"
    if year == 2018 or year == 2019 or year == 2020 or year == 2021 or year == 2022:
        problem_number = "A"
    return problem_number


if __name__ == "__main__":
    year = int(input())
    print(easieat_problem_number_in_the_year_at_ucpc(year=year))