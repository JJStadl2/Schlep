package Models;

/**
 * This class sets details for each bid from a driver to transport an item.
 *
 * Last Updated: 11/18/2020
 *
 * @author josiah Stadler
 */
public class PassCost {

    //Specific info for each driver to set output and cost.
    protected String driverName;
    protected String rating;
    protected double distance;
    protected double rateOfSpeed;
    protected double baseCost;
    protected double eta;
    protected double total;

    //Info that is the same for all drivers for specific delivery/bid.
    protected double instDeliveryCost;
    protected double assistCost;
    protected static double assistTime;
    protected static boolean instRequested;
    protected static boolean assistRequested;

    //Constants for cost calculations- same for all transactions.
    protected static final double TAX = 1.067;
    protected static final double COSTPERMILE = .55;
    protected static final double WAGE = 15.00;
    protected static final double INSTCOST = 25.00;
    protected static final double ZERO = 0;

    //Default/ no arg constructor.
    public PassCost() {

    }

    public PassCost(String _name, String _rating, double _distance, double _speed) {
        this.driverName = _name;
        this.rating = _rating;
        this.distance = _distance;
        this.rateOfSpeed = _speed;
        this.baseCost = _distance * COSTPERMILE;
        this.eta = getEta();
        this.instDeliveryCost = getInstDeliveryCost();
        this.assistCost = getAssistCost();
        this.total = getTotal();
        //assistTime = getAssistTime(); for use in timer
    }

    @Override
    public String toString() {
        return "Thank you for using Schlep!\n"
                + "Your Schlepper, " + this.driverName + " is currently " + this.distance
                + " from your location, and should be there in approximately "
                + this.eta + ".\n"
                + "We will call you when your Schlepper is 30 minutes away\n"
                + "Your total cost is: " + this.total;
    }

    //=================================== GETTERS =============================//
    /**
     * @return WAGE- The hourly wage for all drivers when assisting with
     * offloading.
     */
    public double getWAGE() {
        return WAGE;
    }

    /**
     * @return Instance eta- How long before driver arrives
     */
    public double getEta() {
        this.eta = this.distance / this.rateOfSpeed;
        return this.eta;
    }

    /**
     * @return driver name - The name for the driver responsible for the bid
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @return - Rating of the driver for specific
     */
    public String getRating() {
        return rating;
    }

    /**
     * @return - distance the driver is from drop off location
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @return - the speed the driver is traveling while in route to drop off
     */
    public double getRateOfSpeed() {
        return rateOfSpeed;
    }

    /**
     * @return - instRequested is set in the DeliveryInfo Controller. when a
     * customer requests a same day delivery. This adds a fee to final cost. If
     * it is not selected then this cost is 0.
     */
    public double getInstDeliveryCost() {
        if (instRequested) {
            return INSTCOST;
        } else {
            return ZERO;
        }
    }

    /**
     * @return - the cost of driver assistance for offloading an item.
     */
    public double getAssistCost() {
        return assistCost;
    }

    /**
     *
     * @return - the amount of time the customer has requested for assistance
     */
    public double getAssistTime() {
        return assistTime;
    }

    /**
     *
     * @return - The total cost of a bid for a driver
     */
    public double getTotal() {
        this.total = (this.baseCost + this.instDeliveryCost + this.assistCost) * TAX;
        return total;
    }

    //=========================== SETTERS =====================================
    /**
     * @param _instReq - Is true if customer requested a same day delivery. else
     * false. Set from the DeliveryInfo controller.
     */
    public void setInstRequested(boolean _instReq) {
        instRequested = _instReq;
    }

    /**
     * @param _eta -
     */
    public void setEta(double _eta) {
        this.eta = distance / rateOfSpeed;
    }

    /**
     * @param _driverName -The name of the driver, passed from the Bidpage
     * controller.
     */
    public void setDriverName(String _driverName) {
        this.driverName = _driverName;
    }

    /**
     * @param _rating - Sets the rating of a specific driver. Set in the BidPage
     * Controller.
     */
    public void setRating(String _rating) {
        this.rating = _rating;
    }

    /**
     * @param _distance -Sets the distance a specific driver is from the drop
     * off location. Passed from the bidPage controller.
     */
    public void setDistance(double _distance) {
        this.distance = _distance;
    }

    /**
     * @param _rateOfSpeed - Sets the speed at which the a specific driver is
     * traveling. Passed from bidPage controller.
     */
    public void setRateOfSpeed(double _rateOfSpeed) {
        this.rateOfSpeed = _rateOfSpeed;
    }

    /**
     * @param _assistCost - Sets the assistance cost for a specific transaction.
     * This is the same across all bids for the transaction. Is based on
     * assistTime and passed from the AddictionalNeedsPer controller.
     */
    public void setAssistCost(double _assistCost) {
        this.assistCost = _assistCost;
    }

    /**
     * @param _assistTime - Sets the amount of assistance time requested by
     * customer. Determines assistCost in and is passed from AdditionalItemsPer
     * controller.
     */
    public void setAssistTime(double _assistTime) {
        assistTime = _assistTime;
    }

    public void setTotal(double _total) {
        this.total = _total;
    }

}
