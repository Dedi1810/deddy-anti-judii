# DEDDY ANTI JUDI ONLINE
# DNS Domain Filter

from blocker import check_domain


def filter_request(domain):
    result = check_domain(domain)

    if result == "DIBLOKIR":
        return {
            "domain": domain,
            "status": "BLOCKED",
            "action": "REJECT"
        }

    return {
        "domain": domain,
        "status": "SAFE",
        "action": "ALLOW"
    }


if __name__ == "__main__":
    print("=== DEDDY DNS FILTER ===")

    while True:
        domain = input("\nMasukkan domain: ").strip()

        if domain.lower() == "keluar":
            break

        print(filter_request(domain))
