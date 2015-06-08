package com.analitics.managerialstaff.ui.view.navigations;

import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

import static com.analitics.managerialstaff.backend.model.enums.Department.*;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = DepartmentsView.NAME)
public class DepartmentsViewImpl extends VerticalLayout implements DepartmentsView {

    private Tree departmentTree;
    private Label nameLabel;
    private Label telephoneLabel;
    private Label cabinetLabel;

    public static final String ROOT_ITEM = "Предприятие";
    public static final String GIVEN_MANAGEMENT_ITEM = "Управление учётом";
    public static final String ACCOUNTING_ITEM = "Бухгалтерия";
    public static final String ACCOUNTABILITY_MANAGEMENT_ITEM = "Управление отчётностью";
    public static final String TAXATION_ITEM = "Налогообложение";

    public static final String FIRST_NAME = "Жаблев Инокентий";
    public static final String FIRST_TELEPHONE = "тел. 302-34-56";
    public static final String FIRST_CABINET = "к. 303";

    public static final String SECOND_NAME = "Васильева Светлана";
    public static final String SECOND_TELEPHONE = "тел. 302-34-57";
    public static final String SECOND_CABINET = "к. 302";

    public static final String THIRD_NAME = "Игорев Павел";
    public static final String THIRD_TELEPHONE = "тел. 302-34-58";
    public static final String THIRD_CABINET = "к. 301";

    public static final String FOURTH_NAME = "Петров Иван";
    public static final String FOURTH_TELEPHONE = "тел. 302-34-59";
    public static final String FOURTH_CABINET = "к. 314";

    public static final String FIFTH_NAME = "Иванов Петр";
    public static final String FIFTH_TELEPHONE = "тел. 302-34-60";
    public static final String FIFTH_CABINET = "к. 315";

    @PostConstruct
    private void init() {
        initComponents();
        constructLayout();
    }

    private void initComponents() {
        departmentTree = new Tree();
        departmentTree.addItemClickListener(this::treeItemClicked);

        departmentTree.addItem(ROOT_ITEM);

        departmentTree.addItem(GIVEN_MANAGEMENT_ITEM);
        departmentTree.addItem(ACCOUNTING_ITEM);
        departmentTree.addItem(ACCOUNTABILITY_MANAGEMENT_ITEM);
        departmentTree.addItem(TAXATION_ITEM);

        departmentTree.addItem(FINANCIAL_ACCOUNTING_AND_REPORTING);
        departmentTree.addItem(COST_ACCOUNTING);
        departmentTree.addItem(CHIEF_ACCOUNTANT);
        departmentTree.addItem(CONSOLIDATED_FINANCIAL_STATEMENTS);
        departmentTree.addItem(TAX_REPORTING);

        departmentTree.setParent(GIVEN_MANAGEMENT_ITEM, ROOT_ITEM);
        departmentTree.setParent(FINANCIAL_ACCOUNTING_AND_REPORTING, GIVEN_MANAGEMENT_ITEM);
        departmentTree.setChildrenAllowed(FINANCIAL_ACCOUNTING_AND_REPORTING, false);
        departmentTree.setParent(COST_ACCOUNTING, GIVEN_MANAGEMENT_ITEM);
        departmentTree.setChildrenAllowed(COST_ACCOUNTING, false);
        departmentTree.setParent(ACCOUNTING_ITEM, ROOT_ITEM);
        departmentTree.setParent(CHIEF_ACCOUNTANT, ACCOUNTING_ITEM);
        departmentTree.setChildrenAllowed(CHIEF_ACCOUNTANT, false);
        departmentTree.setParent(ACCOUNTABILITY_MANAGEMENT_ITEM, ROOT_ITEM);
        departmentTree.setParent(CONSOLIDATED_FINANCIAL_STATEMENTS, ACCOUNTABILITY_MANAGEMENT_ITEM);
        departmentTree.setChildrenAllowed(CONSOLIDATED_FINANCIAL_STATEMENTS, false);
        departmentTree.setParent(TAXATION_ITEM, ROOT_ITEM);
        departmentTree.setParent(TAX_REPORTING, TAXATION_ITEM);
        departmentTree.setChildrenAllowed(TAX_REPORTING, false);

        // Expand all items that can be
        departmentTree.getItemIds().forEach(departmentTree::expandItem);

        nameLabel = new Label("---");
        telephoneLabel = new Label("---");
        cabinetLabel = new Label("---");
    }

    private void constructLayout() {
        MHorizontalLayout homeContent = new MHorizontalLayout(
                new MVerticalLayout(
                        new MVerticalLayout(
                                departmentTree
                        )
                ).withMargin(false).withStyleName(MyTheme.LAYOUT_CARD).withHeight("50%")
                        .withCaption("Иерархия отделов предприятия"),
                new MVerticalLayout(
                        new MVerticalLayout(
                                nameLabel,
                                telephoneLabel,
                                cabinetLabel
                        )
                ).withMargin(false).withStyleName(MyTheme.LAYOUT_CARD).withHeight("50%")
                        .withCaption("Информация")
        ).withFullHeight().withFullWidth().withMargin(true);
        addComponent(homeContent);
    }

    private void treeItemClicked(ItemClickEvent event) {
        if (event.getItemId() instanceof Department) {
            switch ((Department) event.getItemId()) {
                case CHIEF_ACCOUNTANT:
                    nameLabel.setValue(FIRST_NAME);
                    telephoneLabel.setValue(FIRST_TELEPHONE);
                    cabinetLabel.setValue(FIRST_CABINET);
                    break;
                case CONSOLIDATED_FINANCIAL_STATEMENTS:
                    nameLabel.setValue(SECOND_NAME);
                    telephoneLabel.setValue(SECOND_TELEPHONE);
                    cabinetLabel.setValue(SECOND_CABINET);
                    break;
                case COST_ACCOUNTING:
                    nameLabel.setValue(THIRD_NAME);
                    telephoneLabel.setValue(THIRD_TELEPHONE);
                    cabinetLabel.setValue(THIRD_CABINET);
                    break;
                case FINANCIAL_ACCOUNTING_AND_REPORTING:
                    nameLabel.setValue(FOURTH_NAME);
                    telephoneLabel.setValue(FOURTH_TELEPHONE);
                    cabinetLabel.setValue(FOURTH_CABINET);
                    break;
                case TAX_REPORTING:
                    nameLabel.setValue(FIFTH_NAME);
                    telephoneLabel.setValue(FIFTH_TELEPHONE);
                    cabinetLabel.setValue(FIFTH_CABINET);
                    break;
            }
        } else {
            nameLabel.setValue("---");
            telephoneLabel.setValue("---");
            cabinetLabel.setValue("---");
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
