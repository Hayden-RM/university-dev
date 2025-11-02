import math

class TradingBot:
    @staticmethod
    def option_price(s, k, r, v, t, n, call=True):
        delta_t = t / n
        u = math.exp(v * math.sqrt(delta_t))
        d = 1 / u
        p = (math.exp(r * delta_t) - d) / (u - d)
        
        prices = [[0.0 for _ in range(n + 1)] for _ in range(n + 1)]
        values = [[0.0 for _ in range(n + 1)] for _ in range(n + 1)]

        # Generate binomial price tree
        for i in range(n + 1):
            for j in range(i + 1):
                prices[i][j] = s * (u ** j) * (d ** (i - j))

        # Option value at maturity
        for j in range(n + 1):
            payoff = prices[n][j] - k if call else k - prices[n][j]
            values[n][j] = max(0, payoff)

        # Backward induction for option price
        for i in range(n - 1, -1, -1):
            for j in range(i + 1):
                values[i][j] = math.exp(-r * delta_t) * (
                    p * values[i + 1][j + 1] + (1 - p) * values[i + 1][j]
                )

        return values[0][0]

    @staticmethod
    def execute_trade(intrinsic_val, actual_market_val, amount):
        execute = False

        if amount < 1:
            print("Not buying/selling at this point")
        else:
            if intrinsic_val < actual_market_val:
                print(f"Buying {amount:.2f} contracts...")
                execute = True
            else:
                print(f"Selling {amount:.2f} contracts...")
                execute = True

        return execute

    @staticmethod
    def calculate_amount(investment_amount, market_value, intrinsic_value, opt_multiplier, historical_prices):
        sd = TradingBot.calculate_sd(historical_prices)
        delta = (market_value - intrinsic_value) / intrinsic_value
        n = (investment_amount * (1 + delta)) / (intrinsic_value * opt_multiplier * sd)
        return n

    @staticmethod
    def calculate_sd(historical_prices):
        mean = TradingBot.calculate_mean(historical_prices)
        sum_of_squares = sum((price - mean) ** 2 for price in historical_prices)
        n = len(historical_prices)
        var = sum_of_squares / (n - 1)
        sd = math.sqrt(var)
        return sd

    @staticmethod
    def calculate_mean(historical_prices):
        return sum(historical_prices) / len(historical_prices)


if __name__ == "__main__":
    s = 2.0   # underlying asset price
    k = 3.0   # strike price
    r = 0.05  # risk-free interest rate
    v = 0.2   # volatility
    t = 1.0   # time to expiration (in years)
    n = 100   # number of time steps
    call = True  # True = call option, False = put option

    option_price = TradingBot.option_price(s, k, r, v, t, n, call)
    investment_amount = 500.0
    opt_multiplier = 100

    historical_prices = [89.0, 76.0, 56.0, 90.0, 95.0, 100.0]
    calc_amount = TradingBot.calculate_amount(investment_amount, s, option_price, opt_multiplier, historical_prices)
    execute = TradingBot.execute_trade(option_price, s, calc_amount)

    print(f"Option price: {option_price:.6f}")
