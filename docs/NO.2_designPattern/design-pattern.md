#设计模式
目录：
<!-- TOC -->
- [设计模式](#设计模式)
- [设计模式简介](#设计模式简介)
- [设计模式六大原则](#设计模式六大原则)
- [创建型模式](#创建型模式)
    - [工厂模式](#工厂模式)
    - [抽象工厂模式](#抽象工厂模式)
    - [单例模式](#单例模式)
    - [建造者模式](#建造者模式)
    - [原型模式](#原型模式)
- [结构型模式](#结构型模式)
    - [适配器模式](#适配器模式)
    - [桥接模式](#桥接模式)
    - [过滤器模式](#过滤器模式)
    - [组合模式](#组合模式)
    - [装饰器模式](#装饰器模式)
    - [外观模式](#外观模式)
    - [享元模式](#享元模式)
    - [代理模式](#代理模式)
- [行为型模式](#行为型模式)
     - [责任链模式](#责任链模式)
     - [命令模式](#命令模式)
     - [解释器模式](#解释器模式)
     - [迭代器模式](#迭代器模式)
     - [中介者模式](#中介者模式)
     - [备忘录模式](#备忘录模式)
     - [观察者模式](#观察者模式)
     - [状态模式](#状态模式)
     - [空对象模式](#空对象模式)
     - [策略模式](#策略模式)
     - [模板模式](#模板模式)
     - [访问者模式](#访问者模式)
<!-- /MarkdownTOC -->

#设计模式
“设计模式”这个术语最初并不是出现在软件设计中，而是被用于建筑领域的设计中。
## 设计模式简介

## 设计模式六大原则
- **开闭原则:** 对扩展开放，对修改关闭。多使用抽象类和接口
- **依赖倒转原则:** 要依赖的与抽象，不要依赖于具体，针对接口编程，不针对实现编程
- **里氏替换原则:** 基类可以被子类替换，使用抽象类继承，不适用具体类继承
- **单一职责原则（合成复用原则）:** 就一个类而言，应该只有一个引起它变化的原因
- **接口隔离原则:** 使用多个隔离接口，而不是使用一个总接口
- **迪米特法则:** 一个软件实体应当尽可能少的与其他实体发生作用，也叫有熟人好办事
## 创建型模式

- **工厂模式:**[工厂模式(Factory Pattern)](creationalPattern/NO.1-factory-pattern.md)
- **抽象工厂模式:**[抽象工厂模式(Abstract Factory Pattern)](creationalPattern/NO.2-abstract-factory-pattern.md)
- **单例模式:**[单例模式(Singleton Pattern)](creationalPattern/NO.3-singleton-pattern.md)
- **建造者模式:**[建造者模式(Builder Pattern)](creationalPattern/NO.4-builder-pattern.md)
- **原型模式:**[原型模式(Prototype Pattern)](creationalPattern/NO.5-prototype-pattern.md)

## 结构型模式

- **适配器模式:**[适配器模式(Adapter Pattern)](structuralPatterns/NO.1-adapter-pattern.md)
- **桥接模式:**[桥接模式(Bridge Pattern)](structuralPatterns/NO.2-bridge-pattern.md)
- **过滤器模式:**[过滤器模式(Filter、Criteria Pattern)](structuralPatterns/NO.3-filter-criteria-pattern.md)
- **组合模式:**[组合模式(Composite Pattern)](structuralPatterns/NO.4-composite-pattern.md)
- **装饰器模式:**[装饰器模式(Decorator Pattern)](structuralPatterns/NO.5-decorator-pattern.md)
- **外观模式:**[外观模式(Facade Pattern)](structuralPatterns/NO.6-facade-pattern.md)
- **享元模式:**[享元模式(Flyweight Pattern)](structuralPatterns/NO.7-flyweight-pattern.md)
- **代理模式:**[代理模式(Proxy Pattern)](structuralPatterns/NO.8-proxy-pattern.md)

## 行为型模式

- **责任链模式:**[责任链模式(Chain of Responsibility Pattern)](behavioralPatterns/NO.1-chain-of-responsibility-pattern.md)
- **命令模式:**[命令模式(Command Pattern)](behavioralPatterns/NO.2-command-pattern.md)
- **解释器模式:**[解释器模式(Interpreter Pattern)](behavioralPatterns/NO.3-interpreter-pattern.md)
- **迭代器模式:**[迭代器模式(Iterator Pattern)](behavioralPatterns/NO.4-iterator-pattern.md)
- **中介者模式:**[中介者模式(Mediator Pattern)](behavioralPatterns/NO.5-mediator-pattern.md)
- **备忘录模式:**[备忘录模式(Memento Pattern)](behavioralPatterns/NO.6-memento-pattern.md)
- **观察者模式:**[观察者模式(Observer Pattern)](behavioralPatterns/NO.7-observer-pattern.md)
- **状态模式:**[状态模式(State Pattern)](behavioralPatterns/NO.8-state-pattern.md)
- **空对象模式:**[空对象模式(Null Object Pattern)](behavioralPatterns/NO.9-null-object-pattern.md)
- **策略模式:**[策略模式(Strategy Pattern)](behavioralPatterns/NO.10-strategy-pattern.md)
- **模板模式:**[模板模式(Template Pattern)](behavioralPatterns/NO.11-template-pattern.md)
- **访问者模式:**[访问者模式(Visitor Pattern)](behavioralPatterns/NO.12-visitor-pattern.md)
