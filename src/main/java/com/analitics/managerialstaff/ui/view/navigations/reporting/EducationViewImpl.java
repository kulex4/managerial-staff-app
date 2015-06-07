package com.analitics.managerialstaff.ui.view.navigations.reporting;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = EducationView.NAME)
public class EducationViewImpl extends VerticalLayout implements EducationView {

    private Chart pieChart;
    private Configuration pieChartConfiguration;

    @PostConstruct
    private void init() {
        setSizeFull();
    }

    @Override
    public void generateChart() {
        removeAllComponents();

        initPieChart();
        initConfiguration();
        setPlotOptions();
        setDataSeries();
        constructLayout();

        pieChart.drawChart(pieChartConfiguration);
    }

    private void initPieChart() {
        pieChart = new Chart(ChartType.PIE);
        pieChart.setWidth(600, Unit.PIXELS);
        pieChart.setHeight(600, Unit.PIXELS);
    }

    private void initConfiguration() {
        pieChartConfiguration = pieChart.getConfiguration();
        pieChartConfiguration.setTitle("Образование сотрудников");
    }

    private void setPlotOptions() {
        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setFormatter("'<b>'+ this.point.name +'</b>: '+ this.y +'%'");
        plotOptions.setDataLabels(dataLabels);
        pieChartConfiguration.setPlotOptions(plotOptions);
    }

    private void setDataSeries() {
        final DataSeries series = new DataSeries("Образование, %");
        series.add(new DataSeriesItem("Среднетехническое", calculatePercentage(2, 431)));
        series.add(new DataSeriesItem("Неоконченное высшее", calculatePercentage(8, 431)));
        series.add(new DataSeriesItem("Второе высшее", calculatePercentage(56, 431)));
        series.add(new DataSeriesItem("Ученая степень", calculatePercentage(4, 431)));
        DataSeriesItem slicedItem = new DataSeriesItem("Высшее", calculatePercentage(361, 431));
        slicedItem.setSliced(true);
        slicedItem.setSelected(true);
        series.add(slicedItem);
        pieChartConfiguration.setSeries(series);
    }

    private void constructLayout() {
        MVerticalLayout pieLayout = new MVerticalLayout(
                pieChart
        ).withMargin(true).withFullHeight().withFullWidth().alignAll(Alignment.TOP_CENTER);
        addComponent(pieLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}

    private float calculatePercentage(float number, float total) {
        return new BigDecimal(number * 100 / total).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
