# DEDDY ANTI JUDI ONLINE
# Mesin pemeriksa domain

import os

DATABASE = "data/blocked_domains.txt"


def load_domains():
    if not os.path.exists(DATABASE):
        return set()

    with open(DATABASE, "r") as file:
        return {
            line.strip().lower()
            for line in file
            if line.strip()
        }


def check_domain(domain):
    blocked = load_domains()

    domain = domain.lower().strip().rstrip(".")

    if domain in blocked or any(
        domain.endswith("." + item)
        for item in blocked
    ):
        return "DIBLOKIR"

    return "AMAN"


if __name__ == "__main__":
    print("=== DEDDY ANTI JUDI ===")

    while True:
        domain = input(
            "\nMasukkan domain (ketik keluar untuk berhenti): "
        ).strip()

        if domain.lower() == "keluar":
            break

        print("HASIL:", check_domain(domain))
