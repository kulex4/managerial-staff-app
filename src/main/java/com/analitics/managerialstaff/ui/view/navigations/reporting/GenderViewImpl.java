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

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = GenderView.NAME)
public class GenderViewImpl extends VerticalLayout implements GenderView {

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
        pieChart.setWidth(100, Unit.PERCENTAGE);
        pieChart.setHeight(100, Unit.PERCENTAGE);
    }

    private void initConfiguration() {
        pieChartConfiguration = pieChart.getConfiguration();
        pieChartConfiguration.setTitle("Гендерная принадлежность");
    }

    private void setPlotOptions() {
        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        plotOptions.setShowInLegend(true);
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        pieChartConfiguration.setPlotOptions(plotOptions);
    }

    private void setDataSeries() {
        final DataSeries series = new DataSeries("Пол, %");
        series.add(new DataSeriesItem("Женщины", 68));
        DataSeriesItem slicedItem = new DataSeriesItem("Мужчины", 32);
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
}
